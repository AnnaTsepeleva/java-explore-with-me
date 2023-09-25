package ru.practicum.explorewithme.dtomain.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class UpdateCommentDto {
    @NotBlank
    private Long id;

    @NotBlank
    @Length(max = 3000)
    private String content;
}