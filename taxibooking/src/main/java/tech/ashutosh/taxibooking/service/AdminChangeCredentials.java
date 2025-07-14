package tech.ashutosh.taxibooking.service;

public interface AdminChangeCredentials {

    public String checkAdminCredentials(String oldusername, String oldpassword);
    public String updateAdminCredentials(String newusername, String newpassword,String oldusername);
}
