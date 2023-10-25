//package com.gdscGCC.ghostform.Repository;
//
//import com.gdscGCC.ghostform.Entity.User;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.stream.IntStream;
//
//public class UserRepositoryTest {
//    @Autowired
//    UserRepository userRepository;
//
//    @Test
//    public void InsertDummies() {
//
//        IntStream.rangeClosed(1, 10).forEach(i -> {
//            User user = User.builder()
//                    .userText("Sample..." + i)
//                    .build();
//            //Create!
//            userRepository.save(user);
//        });
//    }
