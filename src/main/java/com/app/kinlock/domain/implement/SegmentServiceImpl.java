package com.app.kinlock.domain.implement;

import com.app.kinlock.data.GenericRepository;
import com.app.kinlock.data.SegmentRepository;
import com.app.kinlock.domain.entity.Segment;
import com.app.kinlock.domain.service.SegmentService;
import com.app.kinlock.exceptions.DuplicatedException;
import com.app.kinlock.presentation.dto.SegmentDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SegmentServiceImpl extends CRUDServiceImpl<Segment, Integer> implements SegmentService {

    private final SegmentRepository repository;

    @Override
    protected GenericRepository<Segment, Integer> getRepository() {
        return repository;
    }

    @Override
    public Segment create(SegmentDto dto) {
        if (repository.existsByName(dto.getName())) {
            throw new DuplicatedException("El tipo de segmento " + dto.getName() + " ya existe");
        }
        Segment segment = new Segment();
        segment.setName(dto.getName());
        return this.create(segment);
    }

    @Override
    public Segment update(Integer id, SegmentDto dto) {
        Segment segment = this.getById(id);
        if (repository.existsByNameAndIdNot(dto.getName(), id)) {
            throw new DuplicatedException("El tipo de segmento " + dto.getName() + " ya existe");
        }
        segment.setName(dto.getName());
        return repository.save(segment);
    }

    @Override
    public Segment getByName(String name) {
        return repository.findByName(name);
    }
}