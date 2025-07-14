package tech.ashutosh.taxibooking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tech.ashutosh.taxibooking.dao.AdminDao;
import tech.ashutosh.taxibooking.model.Admin;

import java.util.Optional;

@Service
public class AdminChangeCredentialsImpl implements AdminChangeCredentials{

    private AdminDao adminDao;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setAdminDao(AdminDao adminDao) {
        this.adminDao = adminDao;
    }

    public String checkAdminCredentials(String oldusername, String oldpassword) {

        //Check the username is in database or not
        Optional<Admin> byUsername= adminDao.findByUsername(oldusername);
        if(byUsername.isPresent()) {
            Admin admin=byUsername.get();
            boolean matches= passwordEncoder.matches(oldpassword, admin.getPassword());

            //Check for the password
            if(matches) {
                return "Success";
            } else {
                return "Wrong Old1 Credentials";
            }
        }
        else {
            return "Wrong Old Credentials";
        }

    }

    @Override
    public String updateAdminCredentials(String newusername, String newpassword, String oldusername) {

       int updateCredentials = adminDao.updateCredentials(newusername, passwordEncoder.encode(newpassword), oldusername);

       if(updateCredentials==1) {
           return "Credentials changed successfully";
       }
       else {
           return "Failed to update";
       }
    }
}
