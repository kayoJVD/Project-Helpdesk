package com.kayo.helpdesk.dto.ticket;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TicketCreateRequestDTO(
        @NotBlank
        @Size(min = 10, max = 500)
        String title,

        @NotBlank
        @Size(min = 10, max = 500)
        String description
) {
}
