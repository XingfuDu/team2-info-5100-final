public class LeadForm {
    private String car, model, name, phone, email, comments, operation;

    public LeadForm() {
    }

    public LeadForm(LeadForm form) {
        this.car = car;
        this.model = model;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.comments = comments;
        this.operation = operation;
    }

    public LeadForm(String car, String model, String name, String phone, String email, String comments, String operation) {
        this.car = car;
        this.model = model;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.comments = comments;
        this.operation = operation;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
