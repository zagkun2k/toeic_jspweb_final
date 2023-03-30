use toieconline;
INSERT INTO exercise(name, createddate, type) VALUES ('Bài tập nghe 1', CURRENT_TIMESTAMP, 'listening');
INSERT INTO exercise(name, createddate, type) VALUES ('Bài tập đọc 1', CURRENT_TIMESTAMP, 'reading');

INSERT INTO exercisequestion (image, audio, question, option1, option2, option3, option4, correctanswer, createddate, exerciseid) VALUES ('exercise/image_1.jpg', 'exercise/audio_1.mp3', 'Look at the picture and listen to the sentences in the Part 1 TOEIC Test. Choose the sentence that best describes the picture:',
                                                                                                                                          'A', 'B', 'C', 'D', 'A', CURRENT_TIMESTAMP, 1);
INSERT INTO exercisequestion (image, audio, question, option1, option2, option3, option4, correctanswer, createddate, exerciseid) VALUES ('exercise/image_2.jpg', 'exercise/audio_2.mp3', 'Look at the picture and listen to the sentences in the Part 1 TOEIC Test. Choose the sentence that best describes the picture:',
                                                                                                                                          'A', 'B', 'C', 'D', 'B', CURRENT_TIMESTAMP, 1);
INSERT INTO exercisequestion (image, audio, question, option1, option2, option3, option4, correctanswer, createddate, exerciseid) VALUES ('exercise/image_3.jpg', 'exercise/audio_3.mp3', 'Look at the picture and listen to the sentences in the Part 1 TOEIC Test. Choose the sentence that best describes the picture:',
                                                                                                                                          'A', 'B', 'C', 'D', 'C', CURRENT_TIMESTAMP, 1);
INSERT INTO exercisequestion (image, audio, question, option1, option2, option3, option4, correctanswer, createddate, exerciseid) VALUES ('exercise/image_4.jpg', 'exercise/audio_4.mp3', 'Look at the picture and listen to the sentences in the Part 1 TOEIC Test. Choose the sentence that best describes the picture:',
                                                                                                                                          'A', 'B', 'C', 'D', 'D', CURRENT_TIMESTAMP, 1);
INSERT INTO exercisequestion (question, option1, option2, option3, option4, correctanswer, createddate, exerciseid) VALUES ('I dont think he will remember the appointment you remind him.','so', 'if', 'unless', 'lest', 'C', CURRENT_TIMESTAMP, 2);
INSERT INTO exercisequestion (question, option1, option2, option3, option4, correctanswer, createddate, exerciseid) VALUES ('The river has overflowed his banks _____ it has been raining continuously for several days.','still', 'yet', 'when', 'as', 'D', CURRENT_TIMESTAMP, 2);
INSERT INTO exercisequestion (question, option1, option2, option3, option4, correctanswer, createddate, exerciseid) VALUES ('Those village folk are poor _____ they always seem so contented.','still', 'yet', 'when', 'as', 'C', CURRENT_TIMESTAMP, 2);
INSERT INTO exercisequestion (question, option1, option2, option3, option4, correctanswer, createddate, exerciseid) VALUES ('he was not interested in music, he agreed to go to the concert.','still', 'yet', 'when', 'as', 'A', CURRENT_TIMESTAMP, 2);

INSERT INTO examination(name, createddate) VALUES ('Bài thi 1', CURRENT_TIMESTAMP);

use toieconline;
INSERT INTO examinationquestion(image, audio, option1, option2, option3, option4, question, correctanswer, createddate,
                                examinationid, type)
VALUES ('examination/image_1.jpg', 'examination/audio_1.mp3',
        'A. The man is diving into the water',
        'B. The man is fishing from the railing',
        'C. The man is packing up his fishing gear',
        'D. The man is purchasing some fish',
        'Look at the picture and listen to the sentences in the Part 1 TOEIC Test. Choose the sentence that best describes the picture:',
        'A', CURRENT_TIMESTAMP, 1, 'PHOTO');
INSERT INTO examinationquestion(image, audio, option1, option2, option3, option4, question, correctanswer, createddate,
                                examinationid, type)
VALUES ('examination/image_2.jpg', 'examination/audio_2.mp3',
        'A. The man is diving into the water',
        'B. The man is fishing from the railing',
        'C. The man is packing up his fishing gear',
        'D. The man is purchasing some fish',
        'Look at the picture and listen to the sentences in the Part 1 TOEIC Test. Choose the sentence that best describes the picture:',
        'B', CURRENT_TIMESTAMP, 1, 'PHOTO');
INSERT INTO examinationquestion(audio, option1, option2, option3, option4, question, correctanswer, createddate,
                                examinationid, type)
VALUES ('examination/audio_3.mp3',
        'A. The man is diving into the water',
        'B. The man is fishing from the railing',
        'C. The man is packing up his fishing gear',
        'D. The man is purchasing some fish',
        'Look at the picture and listen to the sentences in the Part 1 TOEIC Test. Choose the sentence that best describes the picture:',
        'B', CURRENT_TIMESTAMP, 1, 'QUESTION_RESPONSE');
INSERT INTO examinationquestion(audio, option1, option2, option3, option4, question, correctanswer, createddate,
                                examinationid, type)
VALUES ('examination/audio_4.mp3',
        'A. The man is diving into the water',
        'B. The man is fishing from the railing',
        'C. The man is packing up his fishing gear',
        'D. The man is purchasing some fish',
        'Listen to the question and the three responses. Choose the response that best answers the question:',
        'A', CURRENT_TIMESTAMP, 1, 'QUESTION_RESPONSE');
INSERT INTO examinationquestion
(question, paragraph, createddate, examinationid, type)
VALUES ('Read the passage and choose the correct answer:',
        'Eli Finance, the largest real estate financier in the Middle East by market value and total assets, today proudly announced that it received the award for the “Best Financial Services Company in the UAE” during the 2008 Liquid Real Estate Awards ceremony organized by Euromoney. Euromoney Liquid Real Estate Awards honor the world’s leading institutions for their ability to innovate and develop new products and services to meet the market’s demand in today’s increasingly challenging financial environment, as well as make the most efficient use of the inherent strengths within their organization. Speaking on the occasion, Mr. Ismael Alharmi, Chief Executive Officer of Eli Finance said, “We are honored to receive this prestigious award and I would like to thank our staff at Eli for their efforts',
        CURRENT_TIMESTAMP, 1, 'SINGLE_PASSAGE');
INSERT INTO examinationquestion(question, option1, option2, option3, option4, correctanswer, createddate, examinationid, type)
VALUES ('What is the subject of the news report?',
        'A. A national park', 'B. A local zoo', 'C. Commercial products', 'D. Landscaping land', 'B', CURRENT_TIMESTAMP,
        1, 'SINGLE_PASSAGE');
INSERT INTO examinationquestion(question, option1, option2, option3, option4, correctanswer, createddate, examinationid, type)
VALUES ('According to the speaker, what does Algonquin National Park have?',
        'A. Unique rock formations', 'B. A lot of different animals', 'C. Unusual potted plants',
        'D. Beautiful waterfalls', 'A', CURRENT_TIMESTAMP, 1, 'SINGLE_PASSAGE');
INSERT INTO examinationquestion(question, option1, option2, option3, option4, correctanswer, createddate, examinationid, type)
VALUES ('What do some people expect will happen?',
        'A. The wildlife will relocate', 'B. It will increase local business', ' C. New homes will be built',
        'D. They will change their minds', 'B', CURRENT_TIMESTAMP, 1, 'SINGLE_PASSAGE');
