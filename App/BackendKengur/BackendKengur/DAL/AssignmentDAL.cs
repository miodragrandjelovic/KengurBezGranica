using BackendKengur.DAL.Interfaces;
using MongoDB.Driver;
using BackendKengur.Models;
using BackendKengur.Models.Interfaces;
using BackendKengur.DTOComponents;
using System.Collections.Generic;
using System;
using MongoDB.Bson.Serialization;
using MongoDB.Bson;

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

        public List<Assignment> GetAssignmentsByClass(string Class)
        {

            //return _assignments.Find(assignment => Class.Equals(assignment.Class)).ToList();

            return GetRandomTasks(_assignments, Class, 3, 2)
            .Union(GetRandomTasks(_assignments, Class, 4, 2))
            .Union(GetRandomTasks(_assignments, Class, 5, 2))
            .ToList();
        }

        static List<Assignment> GetRandomTasks(IMongoCollection<Assignment> collection, string className, int level, int count)
        {
            var pipeline = new List<BsonDocument>
        {
            BsonDocument.Parse("{ $match: { class: '" + className + "', level: " + level + " } }"), // Filter by Class and Level
            BsonDocument.Parse("{ $sample: { size: " + count + " } }") // Use $sample to get random documents
        };

            var cursor = collection.Aggregate<Assignment>(pipeline);
            var randomTasks = cursor.ToList();

            return randomTasks;
        }

        public List<Assignment> GetTasksFiltered(string Class, int Level)
        {
            return _assignments.Find(assignment => Class.Equals(assignment.Class) && Level == assignment.Level).ToList();
        }

        public bool SendStatistic(List<TaskEfficiencyDTO> list)
        {
            foreach (var task in list) 
            {
                UpdateDefinition<Assignment> update;

                if (task.isCorrect)
                {
                    update = Builders<Assignment>.Update
                    .Inc("correct", 1);
                }
                else
                {
                    update = Builders<Assignment>.Update
                    .Inc("wrong", 1);
                }
                

                var updateAssigment = _assignments.UpdateOne(a => a.Id == task.id, update);

            }
            return true;
        }
    }
}
