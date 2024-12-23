package com.example.LMS.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class  Quiz extends Assesment{
 String type;// MCQ , true/flase , shortanswers
 String topic;

}
