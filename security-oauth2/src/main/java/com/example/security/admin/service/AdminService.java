package com.example.security.admin.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.security.admin.repository.AdminRepository;
import com.example.security.user.user.entity.UserEntity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AdminService implements AdminRepository{

    @Autowired private AdminRepository userRepository;

    @Override
    public UserEntity findByUserAccount(String userAccount) {
        log.info("findByUserAccount param: {}", userAccount);
        return userRepository.findByUserAccount(userAccount);
    }

    @Override
    public List<UserEntity> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<UserEntity> findAll(Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<UserEntity> findAllById(Iterable<Long> ids) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends UserEntity> List<S> saveAll(Iterable<S> entities) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void flush() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public <S extends UserEntity> S saveAndFlush(S entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends UserEntity> List<S> saveAllAndFlush(Iterable<S> entities) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<UserEntity> entities) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> ids) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAllInBatch() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public UserEntity getOne(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UserEntity getById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UserEntity getReferenceById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends UserEntity> List<S> findAll(Example<S> example) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends UserEntity> List<S> findAll(Example<S> example, Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Page<UserEntity> findAll(Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends UserEntity> S save(S entity) {
        String encodedPass = new BCryptPasswordEncoder().encode(entity.getUserPass());
        entity.setUserPass(encodedPass);
        return userRepository.save(entity);
    }

    @Override
    public Optional<UserEntity> findById(Long id) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void delete(UserEntity entity) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> ids) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAll(Iterable<? extends UserEntity> entities) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAll() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public <S extends UserEntity> Optional<S> findOne(Example<S> example) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    @Override
    public <S extends UserEntity> Page<S> findAll(Example<S> example, Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends UserEntity> long count(Example<S> example) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public <S extends UserEntity> boolean exists(Example<S> example) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public <S extends UserEntity, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
        // TODO Auto-generated method stub
        return null;
    }    
    
}
