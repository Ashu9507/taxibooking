package tech.ashutosh.taxibooking.service;

import org.springframework.web.multipart.MultipartFile;
import tech.ashutosh.taxibooking.model.ServiceForm;

import java.util.List;

public interface ServiceFormService {

    public ServiceForm addService(ServiceForm serviceForm, MultipartFile multipartFile) throws Exception;

    public List<ServiceForm> findAllService();
}
