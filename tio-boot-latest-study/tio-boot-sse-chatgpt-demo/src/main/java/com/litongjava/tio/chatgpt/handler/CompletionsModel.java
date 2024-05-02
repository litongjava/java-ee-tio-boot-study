package com.litongjava.tio.chatgpt.handler;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CompletionsModel {
  private String model;
  private List<ChatgptMessage> messages;
  private boolean stream;
}
