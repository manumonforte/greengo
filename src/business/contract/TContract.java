package business.contract;

public class TContract {

    private Integer serviceLevel;
    private Integer idMainOffice;
    private Integer idService;
    private Boolean active;

    public TContract() {
    }

    public TContract(Integer idMainOffice, Integer idService, Integer serviceLevel, Boolean active) {
        this.serviceLevel = serviceLevel;
        this.idMainOffice = idMainOffice;
        this.idService = idService;
        this.active = active;
    }

    public Integer getServiceLevel() {
        return serviceLevel;
    }

    public void setServiceLevel(Integer serviceLevel) {
        this.serviceLevel = serviceLevel;
    }

    public Integer getIdMainOffice() {
        return idMainOffice;
    }

    public void setIdMainOffice(Integer idMainOffice) {
        this.idMainOffice = idMainOffice;
    }

    public Integer getIdService() {
        return idService;
    }

    public void setIdService(Integer idService) {
        this.idService = idService;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString(){
        String ret = "";
        ret += String.format("%-10s %10s %n", "Main office id: ", idMainOffice);
        ret += String.format("%-13s %13s %n", "service id: ", idService);
        ret += String.format("%-13s %13s %n", "Active: ", active);

        return ret;
    }
}
