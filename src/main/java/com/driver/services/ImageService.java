package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository;
    @Autowired
    ImageRepository imageRepository;

    public Image addImage(Integer blogId, String description, String dimensions) {
        Blog blog = blogRepository.findById(blogId).get();
        Image image = new Image(blog,description,dimensions);
        blog.getImageList().add(image);
        blogRepository.save(blog);
        return image;
    }

    public void deleteImage(Integer id){
        imageRepository.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        String [] scrarray = screenDimensions.split("X"); //A=Length   X    B=Breadth
        Image image = imageRepository.findById(id).get();

        String imageDimensions = image.getDimensions();
        String []imageArray = imageDimensions.split("X");

        int scrL = Integer.parseInt(scrarray[0]);
        int scrB = Integer.parseInt(scrarray[1]);

        int imgL = Integer.parseInt(imageArray[0]);
        int imgB = Integer.parseInt(imageArray[1]);

        return no_Images(scrL,scrB,imgL,imgB);

    }

    private int no_Images(int scrL, int scrB, int imgL, int imgB) {
        int lenC = scrL/imgL;
        int lenB = scrB/imgB;
        return lenC * lenB;
    }
}