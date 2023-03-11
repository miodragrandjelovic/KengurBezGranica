using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;

namespace BackendKengur.Models
{
    public class School
    {
        [BsonId]
        [BsonRepresentation(BsonType.ObjectId)]
        public string Id { get; set; } = string.Empty;

        [BsonElement("name")]
        public string Name { get; set; } = string.Empty;

        [BsonElement("city")]
        public string City { get; set; } = string.Empty;

    }
}
