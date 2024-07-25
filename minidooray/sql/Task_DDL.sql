CREATE TABLE Project (
    project_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    project_name VARCHAR(255) NOT NULL,
    project_status ENUM('ACTIVE', 'DORMANT', 'ENDED') NOT NULL
);

CREATE TABLE Milestone (
    milestone_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    milestone_name VARCHAR(255) NOT NULL,
    milestone_progress INTEGER NOT NULL,
    project_id BIGINT,
    FOREIGN KEY (project_id) REFERENCES Project(project_id) ON DELETE CASCADE
);

CREATE TABLE Task (
    task_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    task_name VARCHAR(255) NOT NULL,
    task_description TEXT,
    task_status ENUM('TODO', 'IN_PROGRESS', 'DONE') NOT NULL,
    project_id BIGINT,
    milestone_id BIGINT,
    FOREIGN KEY (project_id) REFERENCES Project(project_id) ON DELETE CASCADE,
    FOREIGN KEY (milestone_id) REFERENCES Milestone(milestone_id) ON DELETE CASCADE
);

CREATE TABLE Tag (
    tag_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    tag_name VARCHAR(255) NOT NULL,
    project_id BIGINT,
    FOREIGN KEY (project_id) REFERENCES Project(project_id) ON DELETE CASCADE
);

CREATE TABLE TaskTag (
    task_id BIGINT,
    tag_id BIGINT,
    FOREIGN KEY (task_id) REFERENCES Task(task_id) ON DELETE CASCADE,
    FOREIGN KEY (tag_id) REFERENCES Tag(tag_id) ON DELETE CASCADE
);

CREATE TABLE Comment (
    comment_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    comment_content TEXT NOT NULL,
    task_id BIGINT,
    FOREIGN KEY (task_id) REFERENCES Task(task_id) ON DELETE CASCADE
);

CREATE TABLE ProjectMember (
    member_id BIGINT NOT NULL,
    project_id BIGINT NOT NULL,
    projectmember_role ENUM('admin', 'member') NOT NULL,
    PRIMARY KEY (member_id),
    FOREIGN KEY (project_id) REFERENCES Project(project_id) ON DELETE CASCADE
);