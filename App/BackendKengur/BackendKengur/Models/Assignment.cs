using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;

namespace BackendKengur.Models
{
    public class Assignment
    {
        [BsonId]
        [BsonRepresentation(BsonType.ObjectId)]
        public string Id { get; set; } = string.Empty;

        [BsonElement("taskText")]
        public string TaskText { get; set; } = string.Empty;

        [BsonElement("taskPicture")]
        public string TaskPicture { get; set; } = string.Empty;

        [BsonElement("answersText")]
        public List<string> AnswersText { get; set; } = new List<string>();

        [BsonElement("answersPictures")]
        public List<string> AnswersPictures { get; set; } = new List<string>();

        [BsonElement("correctAnswerIndex")]
        public int CorrectAnswerIndex { get; set; } = 0;

        [BsonElement("level")]
        public int Level { get; set; } = 3;

        [BsonElement("class")]
        public string Class { get; set; } = string.Empty;
    }
}
