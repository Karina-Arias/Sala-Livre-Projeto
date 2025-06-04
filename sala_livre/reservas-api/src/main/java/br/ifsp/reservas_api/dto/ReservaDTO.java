package br.ifsp.reservas_api.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReservaDTO {
    private Long salaId;
    private LocalDate data;
    private LocalTime horaInicio;
    private LocalTime horaFim;

    public ReservaDTO() {}

    public Long getSalaId() { return salaId; }
    public void setSalaId(Long salaId) { this.salaId = salaId; }

    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }

    public LocalTime getHoraInicio() { return horaInicio; }
    public void setHoraInicio(LocalTime horaInicio) { this.horaInicio = horaInicio; }

    public LocalTime getHoraFim() { return horaFim; }
    public void setHoraFim(LocalTime horaFim) { this.horaFim = horaFim; }
}
