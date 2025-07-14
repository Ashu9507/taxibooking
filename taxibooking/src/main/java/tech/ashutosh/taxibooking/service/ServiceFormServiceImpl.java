package tech.ashutosh.taxibooking.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tech.ashutosh.taxibooking.dao.ServiceFormCrud;
import tech.ashutosh.taxibooking.model.ServiceForm;

import java.io.FileOutputStream;
import java.util.List;

@Service
public class ServiceFormServiceImpl implements ServiceFormService {

    private ServiceFormCrud serviceFormCrud;

    @Autowired
    public void setServiceFormCrud(ServiceFormCrud serviceFormCrud) {
        this.serviceFormCrud = serviceFormCrud;
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public ServiceForm addService(ServiceForm serviceForm, MultipartFile multipartFile)throws Exception {

        ServiceForm save=null;
        System.out.println(serviceForm);

        try {
            save= serviceFormCrud.save(serviceForm);
            if(save!=null) {
                String path = "D:\\taxibooking\\taxibooking\\src\\main\\resources\\static\\myserviceimages\\" + multipartFile.getOriginalFilename();
                byte[] bytes = multipartFile.getBytes();

                FileOutputStream fos = new FileOutputStream(path);
                fos.write(bytes);
            }
        }
        catch (Exception e) {
            throw e;
        }
        System.out.println("Save= "+save);
        return save;
    }

    @Override
    public List<ServiceForm> findAllService() {

        List<ServiceForm> serviceForms = serviceFormCrud.findAll();

        return serviceForms;
    }
}
