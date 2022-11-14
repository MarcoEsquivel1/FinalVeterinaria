package modelos;

public class cls_usuario {
    int id;
    String fullname;
    String username;
    String password;
    String tel;
    int idpermiso;

    public cls_usuario() {
    }

    public cls_usuario(int id, String fullname, String username, String password, String tel, int idpermiso) {
        this.id = id;
        this.fullname = fullname;
        this.username = username;
        this.password = password;
        this.tel = tel;
        this.idpermiso = idpermiso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getIdpermiso() {
        return idpermiso;
    }

    public void setIdpermiso(int idpermiso) {
        this.idpermiso = idpermiso;
    }
}
