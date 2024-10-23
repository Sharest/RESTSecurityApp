package services;

import DTO.ViewDTO;
import jakarta.transaction.Transactional;
import models.View;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import repositories.ViewRepository;

@Component
@Transactional
public class ViewService {
    private final ViewRepository viewRepository;

    public ViewService(ViewRepository viewRepository) {
        this.viewRepository = viewRepository;
    }

    @KafkaListener(topics = "viewTopic")
    public void addView(ViewDTO viewDTO) {
        viewRepository.save(convertViewDTOToView(viewDTO));

    }

    private View convertViewDTOToView(ViewDTO viewDTO) {
        View view = new View();
        view.setUserId(viewDTO.getUserId());
        view.setPostId(viewDTO.getPostId());
        return view;
    }
}
