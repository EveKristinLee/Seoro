package com.seoro.seoro.service.GroupPost;

import com.seoro.seoro.domain.dto.Group.GroupDetailResponseDto;
import com.seoro.seoro.domain.dto.Group.GroupMainResponseDto;
import com.seoro.seoro.domain.dto.Group.GroupSignupRequestDto;
import com.seoro.seoro.domain.dto.GroupPost.GroupPostCreateRequestDto;
import com.seoro.seoro.domain.dto.ResultResponseDto;
import com.seoro.seoro.domain.entity.Groups.*;
import com.seoro.seoro.domain.entity.User.User;
import com.seoro.seoro.repository.ChatRoom.ChatRepository;
import com.seoro.seoro.repository.Group.GroupJoinRepository;
import com.seoro.seoro.repository.Group.GroupRepository;
import com.seoro.seoro.repository.GroupPost.GroupPostRepository;
import com.seoro.seoro.repository.User.UserRepository;
import com.seoro.seoro.service.Group.GroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class GroupPostServiceImpl implements GroupPostService {
    private final UserRepository userRepository;
    private final GroupPostRepository groupPostRepository;
    private final GroupRepository groupRepository;

    @Override
    public ResultResponseDto createGroupPost(GroupPostCreateRequestDto requestDto) {
        ResultResponseDto resultResponseDto = new ResultResponseDto();

        //작성자 확인
        Optional<User> findUser = userRepository.findById(requestDto.getWriter());
        User writer = new User();
        if(findUser.isPresent()) {
            writer = findUser.get();
        } else {
            resultResponseDto.setResult(false);
            return resultResponseDto;
        }
        //그룹 확인
        Optional<Groups> findGroup = groupRepository.findById(requestDto.getGroupId());
        Groups group = new Groups();
        if(findGroup.isPresent()) {
            group = findGroup.get();
        } else {
            resultResponseDto.setResult(false);
            return resultResponseDto;
        }
//        //사진이 있을 때
//        List<GroupPostPhoto> photos = new ArrayList<>();
//        for(String p : requestDto.getPostImage()) {
//            GroupPostPhoto photo = GroupPostPhoto.builder()
//                    .groupPostPhoto(p)
//                    .groupPost()
//                    .build();
//            photos.add()
//        }


        GroupPost saveGroupPost = GroupPost.builder()
                .groupPostContent(requestDto.getPostContent())
                .groups(group)
                .groupPostTime(requestDto.getPostTime())
                .postCategory(PostCategory.valueOf(requestDto.getPostCategory()))
                .user(writer)
//                .photos()
                .build();
        groupPostRepository.save(saveGroupPost);
        resultResponseDto.setResult(true);
        return resultResponseDto;
    }
}