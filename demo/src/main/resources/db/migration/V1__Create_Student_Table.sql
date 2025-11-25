-- Create Student Table
CREATE TABLE IF NOT EXISTS student (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create index on name for better query performance
CREATE INDEX IF NOT EXISTS idx_student_name ON student(name);

-- Add comments to columns
COMMENT ON TABLE student IS 'Student entity table';
COMMENT ON COLUMN student.id IS 'Unique student identifier';
COMMENT ON COLUMN student.name IS 'Student full name';
COMMENT ON COLUMN student.address IS 'Student address';
COMMENT ON COLUMN student.created_at IS 'Record creation timestamp';
COMMENT ON COLUMN student.updated_at IS 'Record last update timestamp';
