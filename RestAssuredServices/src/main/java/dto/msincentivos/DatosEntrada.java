package dto.msincentivos;

import java.util.ArrayList;

public class DatosEntrada {

    public int idPromocion;
    public Object idModalidad;
    public int valorTotal;
    public ArrayList<Apuesta> apuestas;

    public int getIdPromocion() {
        return idPromocion;
    }

    public void setIdPromocion(int idPromocion) {
        this.idPromocion = idPromocion;
    }

    public Object getIdModalidad() {
        return idModalidad;
    }

    public void setIdModalidad(Object idModalidad) {
        this.idModalidad = idModalidad;
    }

    public int getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(int valorTotal) {
        this.valorTotal = valorTotal;
    }

    public ArrayList<Apuesta> getApuestas() {
        return apuestas;
    }

    public void setApuestas(ArrayList<Apuesta> apuestas) {
        this.apuestas = apuestas;
    }
}
