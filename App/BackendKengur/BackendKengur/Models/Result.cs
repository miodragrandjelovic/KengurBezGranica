using BackendKengur.DTOComponents;
using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;

namespace BackendKengur.Models
{
    public class Result
    {

        [BsonId]
        [BsonRepresentation(BsonType.ObjectId)]
        public string Id { get; set; } = string.Empty;

        [BsonElement("userDTO")]
        public UserLeaderboardDTO? User { get; set; }

        [BsonElement("points")]
        public double Points { get; set; } = 0;

        [BsonElement("class")]
        public string Class { get; set; } = string.Empty;

    }
}
