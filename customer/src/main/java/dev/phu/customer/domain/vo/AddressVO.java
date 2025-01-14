package dev.phu.customer.domain.vo;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Embeddable
@Getter
@EqualsAndHashCode
public final class AddressVO {

    private final String houseNumber;
    private final String street;
    private final String subDistrict;
    private final String district;
    private final String province;
    private final String postalCode;
    private final String country;
    private final String recipientName;
    private final String contactNumber;

    // Constructor with validation
    public AddressVO(String houseNumber, String street, String subDistrict, String district, String province,
                     String postalCode, String country, String recipientName, String contactNumber) {
        validate(houseNumber, subDistrict, district, province, postalCode, country, recipientName, contactNumber);
        this.houseNumber = houseNumber.trim();
        this.street = street != null ? street.trim() : null;
        this.subDistrict = subDistrict.trim();
        this.district = district.trim();
        this.province = province.trim();
        this.postalCode = postalCode.trim();
        this.country = country.trim();
        this.recipientName = recipientName.trim();
        this.contactNumber = contactNumber.trim();
    }

    private void validate(String houseNumber, String subDistrict, String district, String province,
                          String postalCode, String country, String recipientName, String contactNumber) {
        if (houseNumber == null || houseNumber.isBlank()) {
            throw new IllegalArgumentException("House number is required");
        }
        if (subDistrict == null || subDistrict.isBlank()) {
            throw new IllegalArgumentException("Sub-district is required");
        }
        if (district == null || district.isBlank()) {
            throw new IllegalArgumentException("District is required");
        }
        if (province == null || province.isBlank()) {
            throw new IllegalArgumentException("Province is required");
        }
        if (postalCode == null || !postalCode.matches("\\d{5}")) {
            throw new IllegalArgumentException("Postal code must be exactly 5 digits");
        }
        if (country == null || country.isBlank()) {
            throw new IllegalArgumentException("Country is required");
        }
        if (recipientName == null || recipientName.isBlank()) {
            throw new IllegalArgumentException("Recipient name is required");
        }
        if (contactNumber == null || !contactNumber.matches("\\+?[0-9]{10,15}")) {
            throw new IllegalArgumentException("Contact number must be valid (10-15 digits).");
        }
    }

    @Override
    public String toString() {
        StringBuilder address = new StringBuilder();
        address.append(recipientName).append(" (").append(contactNumber).append("), ")
                .append(houseNumber);

        if (street != null && !street.isBlank()) {
            address.append(", ").append(street);
        }

        address.append(", ").append(subDistrict)
                .append(", ").append(district)
                .append(", ").append(province)
                .append(", ").append(postalCode)
                .append(", ").append(country);

        return address.toString();
    }
}
