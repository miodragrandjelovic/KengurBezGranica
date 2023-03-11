using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;

namespace BackendKengur.Models
{
    public class Result
    {

        [BsonId]
        [BsonRepresentation(BsonType.ObjectId)]
        public string Id { get; set; } = string.Empty;

        [BsonElement("user")]
        public User? User { get; set; }

        [BsonElement("points")]
        public float Points { get; set; } = 0;

        [BsonElement("class")]
        public short Class { get; set; } = 0;

        [BsonElement("date")]
        public DateTime Date { get; set; } = DateTime.Now;

    }
}
