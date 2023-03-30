use toieconline;
ALTER TABLE exercisequestion ADD COLUMN correctanswer VARCHAR(10) NOT NULL;

use toieconline;
ALTER TABLE examinationquestion ADD COLUMN type VARCHAR(100);
ALTER TABLE exercise ADD COLUMN type VARCHAR(100);