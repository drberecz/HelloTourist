package hu.ps.ht.service;

import hu.ps.ht.dao.CustomerGroupDAO;
import hu.ps.ht.dao.GuideDAO;
import hu.ps.ht.dao.TravellerDAO;
import hu.ps.ht.dao.UserDAO;
import hu.ps.ht.dto.UserDTO;
import hu.ps.ht.entity.Customer;
import hu.ps.ht.entity.CustomerGroup;
import hu.ps.ht.entity.GuideEntity;
import hu.ps.ht.entity.TravellerEntity;
import hu.ps.ht.enumerated.OutcomeMessage;
import hu.ps.ht.enumerated.Role;
import hu.ps.ht.util.RegistrationHashPassword;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.transaction.Transactional;

/**
 *
 * @author Csaba
 */
@Singleton
public class UserService {

    @Inject
    TravellerDAO travellerDAO;

    @Inject
    GuideDAO guideDAO;

    @Inject
    UserDAO userDAO;

    @Inject
    CustomerGroupDAO groupDAO;

    @Transactional
    public OutcomeMessage insertUserRoleBased(UserDTO userDTO, String role) throws IllegalAccessException, InvocationTargetException {

        Customer user = userDAO.checkIfUserExists(userDTO.getUserName());

        if (user != null) {
            return OutcomeMessage.error;
        } else {

            String passwordEncoded = null;
            try {
                passwordEncoded = RegistrationHashPassword.encodeSHA256(userDTO.getPassword());
            } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (Role.TRAVELER.equals(role)) {
                TravellerEntity travellerEntity
                        = TravellerEntity.builder()
                                .userName(userDTO.getUserName())
                                .fullName(userDTO.getFullName())
                                .country(userDTO.getCountry())
                                .imageLink(userDTO.getImageLink())
                                .build();

                travellerDAO.createEntity(travellerEntity);
            } else {

                GuideEntity guideEntity = GuideEntity.builder()
                        .email(userDTO.getEmail())
                        .eventList(new ArrayList<>())
                        .imageLink(userDTO.getImageLink())
                        .numOfReviews(0)
                        .operatesInRegion(userDTO.getOperatesInRegion())
                        .phone(userDTO.getPhone())
                        .reservedDates(new HashSet<>())
                        .scoreSum(0L)
                        .userName(userDTO.getUserName())
                        .build();

                guideDAO.createEntity(guideEntity);
            }

            Customer customer = new Customer();
            customer.setEmail(userDTO.getUserName());
            customer.setName(userDTO.getEmail());
            customer.setPassword(passwordEncoded);

            CustomerGroup cg = (Role.TRAVELER.equals(role))
                    ? groupDAO.findCustomerGroup(Role.TRAVELER)
                    : groupDAO.findCustomerGroup(Role.GUIDE);
            List<CustomerGroup> cgList = new ArrayList<>();
            cgList.add(cg);
            customer.setGroups(cgList);
            userDAO.insertCustomer(customer);

            if (Role.TRAVELER.equals(role)) {
                groupDAO.updateCustomerGroup(Role.TRAVELER, customer);
            } else {
                groupDAO.updateCustomerGroup(Role.GUIDE, customer);
            }

            return OutcomeMessage.success;
        }

    }

}
