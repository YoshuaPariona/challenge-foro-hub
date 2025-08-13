-- 1. USERS
CREATE TABLE users (
    user_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255)
);

-- 2. PROFILES
CREATE TABLE profiles (
    profile_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(255)
);

-- 3. COURSES
CREATE TABLE courses (
    course_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(255),
    category VARCHAR(255)
);

-- 4. TOPICS
CREATE TABLE topics (
    topic_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    title VARCHAR(255),
    message TEXT,
    status VARCHAR(50),
    creation_date DATE,

    user_id BIGINT,
    course_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (course_id) REFERENCES courses(course_id)
);

-- 5. REPLIES
CREATE TABLE replies (
    reply_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    message TEXT,
    solution BOOLEAN,
    creation_date DATE,

    topic_id BIGINT,
    user_id BIGINT,
    FOREIGN KEY (topic_id) REFERENCES topics(topic_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

-- 6. (M-M) USERS - PROFILES
CREATE TABLE user_profiles (
    user_id BIGINT NOT NULL,
    profile_id BIGINT NOT NULL,
	FOREIGN KEY (user_id) REFERENCES users(user_id),
	FOREIGN KEY (profile_id) REFERENCES profiles(profile_id),

    PRIMARY KEY (user_id, profile_id)
);

-- INDEXES
CREATE INDEX idx_users_email ON users(email);
CREATE INDEX idx_topics_user_id ON topics(user_id);
CREATE INDEX idx_topics_course_id ON topics(course_id);
CREATE INDEX idx_replies_topic_id ON replies(topic_id);
CREATE INDEX idx_replies_user_id ON replies(user_id);