package com.majan.admintools.api.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.majan.admintools.api.domain.ContactNumbers.newNumbersList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by dilunika on 3/01/17.
 */
@DisplayName("Contact Number Set")
public class ContactNumbersTest {

    @Test
    @DisplayName("Should contain at-least mobile number.")
    public void shouldContainAtLeastMobileNumber() {

        ContactNumbers cn = newNumbersList()
                            .withMobileNumber("0772322323")
                            .build();

        assertThat(cn.noContactNumberIsSet(), is(false));
        assertThat(cn.getMobileNumber(), is("0772322323"));
    }

    @Test
    @DisplayName("Should contain at-least home number.")
    public void shouldContainAtLeastHomeNumber() {

        ContactNumbers cn = newNumbersList()
                                .withHomeNumber("0112434444")
                                .build();

        assertThat(cn.noContactNumberIsSet(), is(false));
        assertThat(cn.getHomeNumber(), is("0112434444"));
    }

    @Test
    @DisplayName("Should contain at-least work number.")
    public void shouldContainAtLeastWorkNumber() {

        ContactNumbers cn = newNumbersList()
                                .withOfficeNumber("0112455555")
                                .build();

        assertThat(cn.noContactNumberIsSet(), is(false));
        assertThat(cn.getOfficeNumber(), is("0112455555"));
    }

    @Test
    @DisplayName("Should indicate that no number is set.")
    public void shouldIndicateThatNoNumberIsSet() {

        ContactNumbers cn = newNumbersList().build();

        assertThat(cn.noContactNumberIsSet(), is(true));
    }
}
