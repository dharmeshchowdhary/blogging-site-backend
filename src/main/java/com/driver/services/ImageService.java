package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions) {
        Blog blog = blogRepository2.findById(blogId).get();
        Image image = new Image(blog,description,dimensions);
        blog.getImageList().add(image);
        blogRepository2.save(blog);
        return image;
    }

    public void deleteImage(Integer id){
        imageRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        String [] scrarray = screenDimensions.split("X");
        Image image = imageRepository2.findById(id).get();

        String imageDimensions = image.getDimensions();
        String [] imgarray = imageDimensions.split("X");

        int scrL = Integer.parseInt(scrarray[0]);
        int scrB = Integer.parseInt(scrarray[1]);

        int imgL = Integer.parseInt(imgarray[0]);
        int imgB = Integer.parseInt(imgarray[1]);

        return no_Images(scrL,scrB,imgL,imgB);

    }

    private int no_Images(int scrL, int scrB, int imgL, int imgB) {
        int lenC = scrL / imgL; //
        int lenB = scrB / imgB;
        return lenC * lenB;
    }
}