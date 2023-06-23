CREATE TABLE users(
        id BIGSERIAL PRIMARY KEY,
        login TEXT NOT NUll,
        password TEXT NOT NULL);

CREATE index user_login_index ON users (login);
CREATE index user_password_index ON users(login);