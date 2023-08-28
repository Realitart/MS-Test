package com.realitart.mstest.share.mapping.configuration;


import com.realitart.mstest.share.mapping.entity.AnswerMapper;
import com.realitart.mstest.share.mapping.entity.QuestionMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("enhancedModelMapperConfiguration")
public class MappingConfiguration {
    @Bean
    public EnhancedModelMapper modelMapper() {
        return new EnhancedModelMapper();
    }

    @Bean
    public QuestionMapper QuestionMapper() {
        return new QuestionMapper();
    }
    @Bean
    public AnswerMapper AnswerMapper() {
        return new AnswerMapper();
    }
//    @Bean
//    public AssetMapper UserMapper() {
//        return new AssetMapper();
//    }
//    @Bean
//    public AudioMapper AudioMapper() {
//        return new AudioMapper();
//    }
//    @Bean
//    public ImageMapper ImageMapper() {
//        return new ImageMapper();
//    }

}
