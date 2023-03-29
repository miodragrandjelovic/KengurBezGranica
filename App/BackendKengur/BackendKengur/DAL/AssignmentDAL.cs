using BackendKengur.DAL.Interfaces;
using MongoDB.Driver;
using BackendKengur.Models;
using BackendKengur.Models.Interfaces;

namespace BackendKengur.DAL
{
    public class AssignmentDAL : IAssignmentDAL
    {

        private IMongoCollection<Assignment> _assignments;

        public AssignmentDAL(IKengurDatabaseSettings settings, IMongoClient mongoClient)
        {
            var database = mongoClient.GetDatabase(settings.DatabaseName);
            _assignments = database.GetCollection<Assignment>(settings.AssignmentCollectionName);
        }

        public Assignment AddNewAssignment(Assignment assignment)
        {
            _assignments.InsertOne(assignment);
            return assignment;
        }
    }
}
