package com.healthcare.uman.config;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    private String content;
    @Builder.Default
    private final MessageType type = MessageType.blue;
}
