package com.user.service.serviceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import com.user.service.dto.UserDTO;
import com.user.service.entity.Game;
import com.user.service.entity.Review;
import com.user.service.entity.User;
import com.user.service.exceptions.ResourceNotFoundException;
import com.user.service.exceptions.UserAppException;
import com.user.service.feignClients.GameFeignClient;
import com.user.service.repository.UserRepository;
import com.user.service.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private GameFeignClient feignClient;
	
	private User mapToEntity(UserDTO userDTO) {
		User user = modelMapper.map(userDTO, User.class);
		return user;
	}
	
	private UserDTO mapToDto(User user) {
		UserDTO userDTO = modelMapper.map(user, UserDTO.class);
		return userDTO;
	}

	@Override
	public List<UserDTO> findAll() {
		List<User> users = userRepository.findAll();
		if(users.isEmpty()) {
			throw new UserAppException(HttpStatus.NOT_FOUND,"User list is empty");
		}
		return users.stream().map(user -> mapToDto(user)).collect(Collectors.toList());
	}

	
	//RestTemplate
//	@Override
//	public UserDTO findUserById(String userId) {
//		User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
//		
//		Review[] reviewsOfUser = restTemplate.getForObject("http://localhost:8082/api/review/user/"+user.getUserId(), Review[].class);
//		
//		List<Review> reviews = Arrays.asList(reviewsOfUser);
//		
//		List<Review> reviewsList = reviews.stream().map(review ->{
//			
//		ResponseEntity<Game> forEntity = restTemplate.getForEntity("http://localhost:8081/api/game/"+review.getGameId(), Game.class);
//			Game game = forEntity.getBody();
//			
//			review.setGame(game);
//			return review;
//			
//		}).collect(Collectors.toList());
//		
//		user.setReviews(reviewsList);
//		
//		return mapToDto(user);
//	}
	
	
	//FeignClient
	@Override
	public UserDTO findUserById(String userId) {
		User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
		
		Review[] reviewsOfUser = restTemplate.getForObject("http://localhost:8082/api/review/user/"+user.getUserId(), Review[].class);
		
		List<Review> reviews = Arrays.asList(reviewsOfUser);
		
		List<Review> reviewsList = reviews.stream().map(review ->{
			
			Game game = feignClient.getGame(review.getGameId());
			review.setGame(game);
			return review;
			
		}).collect(Collectors.toList());
		
		user.setReviews(reviewsList);
		
		return mapToDto(user);
	}
	

	@Override
	public UserDTO createUser(UserDTO userDTO) {
		User user = mapToEntity(userDTO);
		
		user.setUserId(UUID.randomUUID().toString());
		
		User newUser = userRepository.save(user);
		
		UserDTO saveUser = mapToDto(newUser);
		
		return saveUser;
	}
	
	//Create game from user
	@Override
	public Game createGame(Game game) {
		game.setGameId(UUID.randomUUID().toString());
		Game newGame = feignClient.save(game);
		return newGame;
	}
	
}
