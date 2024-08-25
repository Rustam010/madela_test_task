package com.konkov.CarApp.services;

import com.konkov.CarApp.dto.SubscriberDTO;
import com.konkov.CarApp.entity.Subscriber;
import com.konkov.CarApp.exception.carExceptions.ModelNotExistException;
import com.konkov.CarApp.exception.subscriberExceptions.NotUniqueEmailException;
import com.konkov.CarApp.exception.subscriberExceptions.SubscriberNotCreatedException;
import com.konkov.CarApp.repositories.CarModelRepository;
import com.konkov.CarApp.repositories.SubscriberRepository;
import com.konkov.CarApp.util.MappingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubscriberServiceImpl implements SubscriberService {
    private final SubscriberRepository subscriberRepository;
    private final CarModelRepository carModelRepository;

    private final MappingUtil mappingUtil;

    @Autowired
    public SubscriberServiceImpl(SubscriberRepository subscriberRepository, CarModelRepository carModelRepository,
                                 MappingUtil mappingUtil) {
        this.subscriberRepository = subscriberRepository;
        this.carModelRepository = carModelRepository;
        this.mappingUtil = mappingUtil;
    }

    @Transactional
    @Override
    public void subscribe(SubscriberDTO subscriberDTO, BindingResult bindingResult) {
        validCheck(subscriberDTO, bindingResult);

        Subscriber subscriber = mappingUtil.mapToSubscriber(subscriberDTO);

        subscriberRepository.save(subscriber);

    }

    private void validCheck(SubscriberDTO subscriberDTO, BindingResult bindingResult) {
        //Проверяет что указанный email не подписывается повторно на одну и туже марку
        String email = subscriberDTO.getEmail();
        String model = subscriberDTO.getModel();
        if (subscriberRepository.existsByEmailAndModel(email, model)) {
            String message = String.format("%s уже подписан на модель: %s", email, model);
            throw new NotUniqueEmailException(message);
        }

        //Проверка валидности полей
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMessage.append(error.getField()).append(" - ")
                        .append(error.getDefaultMessage()).append(";");
            }
            throw new SubscriberNotCreatedException(errorMessage.toString());
        }

        //Проверяет что указанная модель существует в nsi_auto_model
        if (!carModelRepository.existsByModel(model)) {
            throw new ModelNotExistException("Марки машины на которую вы хотите подписаться не существует");
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<SubscriberDTO> findAllByModel(String model) {
        List<Subscriber> subscribers = subscriberRepository.findAllByModel(model);
        List<SubscriberDTO> subscribersDTO = new ArrayList<>();

        for (Subscriber subscriber : subscribers) {
            SubscriberDTO subscriberDTO = mappingUtil.mapToSubscriberDTO(subscriber);
            subscribersDTO.add(subscriberDTO);
        }

        return subscribersDTO;
    }
}
