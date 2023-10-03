package dto.superchancepaymentcoupon.respuesta;

public class Saldo {

    public int saldo;
    public Object saldoPorVencer;
    public Object fechaVenceSaldo;
    public long fechaSaldo;

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public Object getSaldoPorVencer() {
        return saldoPorVencer;
    }

    public void setSaldoPorVencer(Object saldoPorVencer) {
        this.saldoPorVencer = saldoPorVencer;
    }

    public Object getFechaVenceSaldo() {
        return fechaVenceSaldo;
    }

    public void setFechaVenceSaldo(Object fechaVenceSaldo) {
        this.fechaVenceSaldo = fechaVenceSaldo;
    }

    public long getFechaSaldo() {
        return fechaSaldo;
    }

    public void setFechaSaldo(long fechaSaldo) {
        this.fechaSaldo = fechaSaldo;
    }
}
