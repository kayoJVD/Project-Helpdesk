package com.kayo.helpdesk.dto.ticket;

public record TicketResponseDTO(
        Long id,
        String title,
        String description,
        String status,
        String userName,
        String techName
) {
}
