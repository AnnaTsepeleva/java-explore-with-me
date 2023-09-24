package ru.practicum.explorewithme.service;

import org.springframework.data.domain.Pageable;
import ru.practicum.explorewithme.dtomain.compilation.CompilationDto;
import ru.practicum.explorewithme.dtomain.compilation.NewCompilationDto;
import ru.practicum.explorewithme.dtomain.request.UpdateCompilationRequest;

import java.util.Collection;

public interface CompilationService {
    Collection<CompilationDto> getAllCompilations(Pageable pageable, Boolean pinned);

    CompilationDto getCompilationById(Long compId);

    CompilationDto saveCompilation(NewCompilationDto newCompilationDto);

    void deleteCompilationById(Long compId);

    CompilationDto changeCompilation(Long compId, UpdateCompilationRequest updateCompilationRequest);
}