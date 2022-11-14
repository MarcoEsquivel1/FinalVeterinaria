package utils;

public class utils {
    public static String convertIdpermiso(int idpermiso){
        if(idpermiso == 1){
            return "Administrador";
        }else if (idpermiso == 2){
            return "Solo Lectura";
        }else if (idpermiso == 3){
            return "Modificación";
        }else {
            return "Otro";
        }
    }
}
