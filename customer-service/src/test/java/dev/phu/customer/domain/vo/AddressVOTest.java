package dev.phu.customer.domain.vo;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AddressVOTest {

    @Test
    void shouldCreateAddressVOSuccessfully() {
        // Given
        String houseNumber = "123";
        String street = "Main Street";
        String subDistrict = "Bang Rak";
        String district = "Bangkok";
        String province = "Bangkok";
        String postalCode = "10110";
        String country = "Thailand";
        String recipientName = "John Doe";
        String contactNumber = "+66812345678";

        // When
        AddressVO addressVO = new AddressVO(houseNumber, street, subDistrict, district, province,
                postalCode, country, recipientName, contactNumber);

        // Then
        assertThat(addressVO).isNotNull();
        assertThat(addressVO.getHouseNumber()).isEqualTo(houseNumber);
        assertThat(addressVO.getStreet()).isEqualTo(street);
        assertThat(addressVO.getSubDistrict()).isEqualTo(subDistrict);
        assertThat(addressVO.getDistrict()).isEqualTo(district);
        assertThat(addressVO.getProvince()).isEqualTo(province);
        assertThat(addressVO.getPostalCode()).isEqualTo(postalCode);
        assertThat(addressVO.getCountry()).isEqualTo(country);
        assertThat(addressVO.getRecipientName()).isEqualTo(recipientName);
        assertThat(addressVO.getContactNumber()).isEqualTo(contactNumber);
    }

    @Test
    void shouldThrowExceptionWhenHouseNumberIsNull() {
        // Given
        String houseNumber = null;

        // When & Then
        assertThatThrownBy(() -> new AddressVO(houseNumber, "Main Street", "Bang Rak", "Bangkok",
                "Bangkok", "10110", "Thailand", "John Doe", "+66812345678"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("House number is required");
    }

    @Test
    void shouldThrowExceptionWhenPostalCodeIsInvalid() {
        // Given
        String invalidPostalCode = "123";

        // When & Then
        assertThatThrownBy(() -> new AddressVO("123", "Main Street", "Bang Rak", "Bangkok",
                "Bangkok", invalidPostalCode, "Thailand", "John Doe", "+66812345678"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Postal code must be exactly 5 digits");
    }

    @Test
    void shouldThrowExceptionWhenContactNumberIsInvalid() {
        // Given
        String invalidContactNumber = "12345";

        // When & Then
        assertThatThrownBy(() -> new AddressVO("123", "Main Street", "Bang Rak", "Bangkok",
                "Bangkok", "10110", "Thailand", "John Doe", invalidContactNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Contact number must be valid (10-15 digits).");
    }

    @Test
    void toStringShouldFormatAddressProperly() {
        // Given
        AddressVO addressVO = new AddressVO("123", "Main Street", "Bang Rak", "Bangkok",
                "Bangkok", "10110", "Thailand", "John Doe", "+66812345678");

        // When
        String formattedAddress = addressVO.toString();

        // Then
        assertThat(formattedAddress).isEqualTo(
                "John Doe (+66812345678), 123, Main Street, Bang Rak, Bangkok, Bangkok, 10110, Thailand"
        );
    }

    @Test
    void toStringShouldHandleNullStreetGracefully() {
        // Given
        AddressVO addressVO = new AddressVO("123", null, "Bang Rak", "Bangkok",
                "Bangkok", "10110", "Thailand", "John Doe", "+66812345678");

        // When
        String formattedAddress = addressVO.toString();

        // Then
        assertThat(formattedAddress).isEqualTo(
                "John Doe (+66812345678), 123, Bang Rak, Bangkok, Bangkok, 10110, Thailand"
        );
    }
}
