```sql
CREATE TABLE `users` (
 `user_id` int(11) NOT NULL AUTO_INCREMENT,
 `username` varchar(255) NOT NULL,
 `password` varchar(255) NOT NULL,
 PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `posts` (
 `post_id` int(11) NOT NULL AUTO_INCREMENT,
 `title` varchar(255) NOT NULL,
 `description` varchar(255) NOT NULL,
 `created_date` TIMESTAMP NOT NULL DEFAULT current_timestamp(),
 `updated_date` TIMESTAMP NOT NULL DEFAULT current_timestamp(),
 `user_id` int(11) NOT NULL,
 PRIMARY KEY (`post_id`),
 FOREIGN KEY (`user_id`) REFERENCES `users`(`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `replies` (
 `reply_id` int(11) NOT NULL AUTO_INCREMENT,
 `content` varchar(255) NOT NULL,
 `created_date` TIMESTAMP NOT NULL DEFAULT current_timestamp(),
 `post_id` int(11) NOT NULL,
 `user_id` int(11) NOT NULL,
 PRIMARY KEY (`reply_id`),
 FOREIGN KEY (`post_id`) REFERENCES `posts`(`post_id`),
 FOREIGN KEY (`user_id`) REFERENCES `users`(`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `likes` (
 `like_id` int(11) NOT NULL AUTO_INCREMENT,
 `post_id` int(11) NOT NULL,
 `user_id` int(11) NOT NULL,
 PRIMARY KEY (`like_id`),
 FOREIGN KEY (`post_id`) REFERENCES `posts`(`post_id`),
 FOREIGN KEY (`user_id`) REFERENCES `users`(`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `chats` (
 `chat_id` int(11) NOT NULL AUTO_INCREMENT,
 `message` varchar(255) NOT NULL,
 `sent_date` TIMESTAMP NOT NULL DEFAULT current_timestamp(),
 `user_id` int(11) NOT NULL,
 PRIMARY KEY (`chat_id`),
 FOREIGN KEY (`user_id`) REFERENCES `users`(`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
```