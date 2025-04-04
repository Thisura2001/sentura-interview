package com.example.senturainterview.Service;

import com.example.senturainterview.Dto.UserDto;
import com.example.senturainterview.Entity.UserEntity;
import com.example.senturainterview.Repository.UserRepo;
import lombok.RequiredArgsConstructor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CrudService {
    private final UserRepo userRepository;

    private static final String base_url = "https://8015b5dbc0724d38882ac90397c27649.weavy.io/api/users";
    private static final String token = "Bearer wys_hMWpXdekxcn9Gc8Ioah3azOllzUZ7l3HN9yB";
    private static final OkHttpClient client = new OkHttpClient();

    public UserDto createUser(UserDto dto) throws IOException {
        UserEntity saved = userRepository.save(toEntity(dto));

        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"),
                "{ \"uid\": \"" + dto.getUid() + "\", \"name\": \"" + dto.getName() + "\" }"
        );

        Request request = new Request.Builder()
                .url(base_url)
                .post(body)
                .addHeader("Authorization", token)
                .addHeader("Content-Type", "application/json")
                .build();

        client.newCall(request).execute().close();

        return toDto(saved);
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    public UserDto getUser(Long id) {
        return userRepository.findById(id).map(this::toDto).orElse(null);
    }

    public UserDto updateUser(Long id, UserDto dto) {
        UserEntity existing = userRepository.findById(id).orElseThrow();
        existing.setUid(dto.getUid());
        existing.setName(dto.getName());
        return toDto(userRepository.save(existing));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    private UserEntity toEntity(UserDto dto) {
        return UserEntity.builder()
                .uid(dto.getUid())
                .name(dto.getName())
                .build();
    }

    private UserDto toDto(UserEntity entity) {
        return UserDto.builder()
                .uid(entity.getUid())
                .name(entity.getName())
                .build();
    }
}
