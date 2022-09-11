package com.springex.jpa.groupmember.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import com.springex.jpa.groupmember.entity.GetByUserIdx;
import com.springex.jpa.groupmember.entity.GroupMemberEntity;
import com.springex.jpa.groupmember.repository.GroupMemberRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GroupMemberService implements GroupMemberRepository {

    @Autowired private GroupMemberRepository groupMemberRepository;

    @Override
    public GetByUserIdx getByUserIdx(Long userIdx) {
        GetByUserIdx result = groupMemberRepository.getByUserIdx(userIdx);
        return result;
    }

    @Override
    public List<GroupMemberEntity> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<GroupMemberEntity> findAll(Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<GroupMemberEntity> findAllById(Iterable<Long> ids) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends GroupMemberEntity> List<S> saveAll(Iterable<S> entities) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void flush() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public <S extends GroupMemberEntity> S saveAndFlush(S entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends GroupMemberEntity> List<S> saveAllAndFlush(Iterable<S> entities) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<GroupMemberEntity> entities) {
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
    public GroupMemberEntity getOne(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GroupMemberEntity getById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GroupMemberEntity getReferenceById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends GroupMemberEntity> List<S> findAll(Example<S> example) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends GroupMemberEntity> List<S> findAll(Example<S> example, Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Page<GroupMemberEntity> findAll(Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends GroupMemberEntity> S save(S entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<GroupMemberEntity> findById(Long id) {
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
    public void delete(GroupMemberEntity entity) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> ids) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAll(Iterable<? extends GroupMemberEntity> entities) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAll() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public <S extends GroupMemberEntity> Optional<S> findOne(Example<S> example) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    @Override
    public <S extends GroupMemberEntity> Page<S> findAll(Example<S> example, Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends GroupMemberEntity> long count(Example<S> example) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public <S extends GroupMemberEntity> boolean exists(Example<S> example) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public <S extends GroupMemberEntity, R> R findBy(Example<S> example,
            Function<FetchableFluentQuery<S>, R> queryFunction) {
        // TODO Auto-generated method stub
        return null;
    }

}