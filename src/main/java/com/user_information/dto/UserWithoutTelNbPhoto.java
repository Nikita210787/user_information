package com.user_information.dto;

/**
 * User details informations
 * @param lastName
 * @param firstName
 * @param patronymic
 * @param password
 * @param dateBirth
 */
public record UserWithoutTelNbPhoto(String lastName, String firstName,
                                         String patronymic, String password, java.time.LocalDate dateBirth) {
}

