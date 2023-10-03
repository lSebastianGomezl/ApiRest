package dto.consultcuponessivemtx.response;

import java.util.ArrayList;

public class CuponDescripcion {

    public int idCupon;
    public String descripcion;
    public ArrayList<AtributosCupon> atributosCupon;
    public InfoGeneralCupones infoGeneralCupones;
    public CuponTyC cuponTyC;

    public int getIdCupon() {
        return idCupon;
    }

    public void setIdCupon(int idCupon) {
        this.idCupon = idCupon;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ArrayList<AtributosCupon> getAtributosCupon() {
        return atributosCupon;
    }

    public void setAtributosCupon(ArrayList<AtributosCupon> atributosCupon) {
        this.atributosCupon = atributosCupon;
    }

    public InfoGeneralCupones getInfoGeneralCupones() {
        return infoGeneralCupones;
    }

    public void setInfoGeneralCupones(InfoGeneralCupones infoGeneralCupones) {
        this.infoGeneralCupones = infoGeneralCupones;
    }

    public CuponTyC getCuponTyC() {
        return cuponTyC;
    }

    public void setCuponTyC(CuponTyC cuponTyC) {
        this.cuponTyC = cuponTyC;
    }
}
