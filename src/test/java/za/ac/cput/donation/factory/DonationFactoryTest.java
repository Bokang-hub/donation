package za.ac.cput.donation.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.donation.entity.Donation;
import za.ac.cput.donation.entity.Donor;

import static org.junit.jupiter.api.Assertions.*;

class DonationFactoryTest {

    @Test
    void createDonation() {
        Donation d = DonationFactory.createDonation(771,"Hungry kids","Food");
        assertNotNull(d);
        assertEquals("Food", d.getDonationType());
        System.out.println(d);
    }
}