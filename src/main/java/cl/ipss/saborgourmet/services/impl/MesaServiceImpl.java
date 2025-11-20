package cl.ipss.saborgourmet.services.impl;

import cl.ipss.saborgourmet.models.Mesa;
import cl.ipss.saborgourmet.repositories.MesaRepository;
import cl.ipss.saborgourmet.services.MesaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MesaServiceImpl implements MesaService {

    private final MesaRepository mesaRepository;

    public MesaServiceImpl(MesaRepository mesaRepository) {
        this.mesaRepository = mesaRepository;
    }

    @Override
    public Mesa save(Mesa mesa) {
        return mesaRepository.save(mesa);
    }

    @Override
    public Mesa findById(Long id) {
        return mesaRepository.findById(id).orElse(null);
    }

    @Override
    public List<Mesa> findAll() {
        return mesaRepository.findAll();
    }

    @Override
    public List<Mesa> findDisponibles(int cantidadPersonas) {
        return mesaRepository.findByAsientosGreaterThanEqualAndDisponibleTrue(cantidadPersonas);
    }

    @Override
    public void delete(Long id) {
        mesaRepository.deleteById(id);
    }
}
