package dto.chanceclasicopaymentcoupon.response;

public class Saldo {
    public int saldo;
    public long fechaSaldo;
    public int totalPuntos;

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public long getFechaSaldo() {
        return fechaSaldo;
    }

    public void setFechaSaldo(long fechaSaldo) {
        this.fechaSaldo = fechaSaldo;
    }

    public int getTotalPuntos() {
        return totalPuntos;
    }

    public void setTotalPuntos(int totalPuntos) {
        this.totalPuntos = totalPuntos;
    }
}
