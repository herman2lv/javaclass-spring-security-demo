-- insert into users (username, password, enabled)
-- values  ('user', 'user', true),
--         ('admin', 'admin', true),
--         ('jack', 'jack', true),
--         ('mike', 'mike', true),
--         ('nick', 'nick', true);

insert into users (username, password, enabled)
values  ('user', '{bcrypt}$2a$10$AsrchpR2Ui8hVAVej6IqWeYPF/W40l75L3KEiyjXIOJB0QDlVGgoK', true),
        ('admin', '{bcrypt}$2a$10$lWp7zFA4NSnw9/gimMeIzul09EEoIIRmrbLyCLk/V8uATSR5WNHDe', true),
        ('jack', '{bcrypt}$2a$10$yCyCZ1GEfioedOeW5lTzU.yR0ywlBWV7BHDLLEFqCqmKt5Qnp8wiW', true),
        ('mike', '{bcrypt}$2a$10$XMVN9lj7YhZ3ra4ly9sYqeJDEBQf1J/4SNN3fMVIn1WFVnqDkcG66', true),
        ('nick', '{bcrypt}$2a$10$7y.ohEYEC6VWkSjqmH2qcOvjAN7POx2t6TTYKCMuS4C3VAPm/h7Lu', true);

insert into authorities (username, authority)
values ('user', 'USER'),
        ('admin', 'USER'),
        ('admin', 'ADMIN'),
        ('jack', 'USER'),
        ('nick', 'USER'),
        ('mike', 'USER'),
        ('mike', 'ADMIN');

INSERT INTO books (author, isbn, title)
VALUES ('Jake', '123-4-56-789012-0', 'Java for Beginners'),
		('Mike', '123-4-56-789012-1', 'Python for Beginners'),
		('Nick', '123-4-56-789012-2', 'Kotlin for Beginners'),
		('Sasha', '123-4-56-789012-3', 'C++ for Beginners'),
		('Kolya', '123-4-56-789012-4', 'C for Beginners'),
		('John', '123-4-56-789012-5', 'C# for Beginners'),
		('Sarah', '123-4-56-789012-6', 'JavaScript for Beginners'),
		('Mary', '123-4-56-789012-7', 'SQL for Beginners'),
		('Tom', '123-4-56-789012-8', 'Git for Beginners'),
		('Lisa', '123-4-56-789012-9', 'Linux for Beginners'),
		('Jake', '123-4-56-789013-0', 'Java for Professionals'),
		('Mike', '123-4-56-789013-1', 'Python for Professionals'),
		('Nick', '123-4-56-789013-2', 'Kotlin for Professionals'),
		('Sasha', '123-4-56-789013-3', 'C++ for Professionals'),
		('Kolya', '123-4-56-789013-4', 'C for Professionals'),
		('John', '123-4-56-789013-5', 'C# for Professionals'),
		('Sarah', '123-4-56-789013-6', 'JavaScript for Professionals'),
		('Mary', '123-4-56-789013-7', 'SQL for Professionals'),
		('Tom', '123-4-56-789013-8', 'Git for Professionals'),
		('Lisa', '123-4-56-789013-9', 'Linux for Professionals'),
		('Jake', '123-4-56-789014-0', 'Java for Experts'),
		('Mike', '123-4-56-789014-1', 'Python for Experts'),
		('Nick', '123-4-56-789014-2', 'Kotlin for Experts'),
		('Sasha', '123-4-56-789014-3', 'C++ for Experts'),
		('Kolya', '123-4-56-789014-4', 'C for Experts'),
		('John', '123-4-56-789014-5', 'C# for Experts'),
		('Sarah', '123-4-56-789014-6', 'JavaScript for Experts'),
		('Mary', '123-4-56-789014-7', 'SQL for Experts'),
		('Tom', '123-4-56-789014-8', 'Git for Experts'),
		('Lisa', '123-4-56-789014-9', 'Linux for Experts');
