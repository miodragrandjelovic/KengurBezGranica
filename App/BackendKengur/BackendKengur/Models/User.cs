using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;
using MongoDB.Driver;

namespace BackendKengur.Models
{
    public class User
    {
        [BsonId]
        [BsonRepresentation(BsonType.ObjectId)]
        public string Id { get; set; } = string.Empty;

        [BsonElement ("firstName")]
        public string FirstName { get; set; } = string.Empty;

        [BsonElement("lastName")]
        public string LastName { get; set; } = string.Empty;

        [BsonElement("email")]
        public string Email { get; set; } = string.Empty;

        [BsonElement("school")]
        public School? School { get; set; }

        [BsonElement("class")]
        public short Class { get; set; }

        [BsonElement("results")]
        public List<MongoDBRef>? Results { get; set; }

        [BsonElement("testNumber")]
        public int TestNumber { get; set; } = 0;

        [BsonElement("sumPoints")]
        public float SumPoints { get; set; } = 0;

        [BsonElement("password")]
        public byte[]? Password { get; set; }

        [BsonElement("passwordKey")]
        public byte[]? PasswordKey { get; set; }

    }
}
