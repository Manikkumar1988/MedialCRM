/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.example.mani.myapplication.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import com.google.api.server.spi.config.Nullable;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.api.server.spi.response.ConflictException;
import com.google.api.server.spi.response.NotFoundException;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.datastore.QueryResultIterator;
import com.googlecode.objectify.cmd.Query;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;

import static com.example.mani.myapplication.backend.OfyService.ofy;

/** An endpoint class we are exposing */
@Api(name = "myApi", version = "v1", namespace = @ApiNamespace(ownerDomain = "backend.myapplication.mani.example.com", ownerName = "backend.myapplication.mani.example.com", packagePath = ""))
public class MyEndpoint {

  /** A simple endpoint method that takes a name and says Hi back */
  @ApiMethod(name = "sayHi1") public MyBean sayHi(@Named("name") String name) {
    MyBean response = new MyBean();
    response.setData("Hi, " + name +" bye");

    return response;
  }


  @ApiMethod(name = "listSalesRep")
  public CollectionResponse<SalesRep> listSalesRep(@Nullable @Named("cursor") String cursorString,
      @Nullable @Named("count") Integer count) {
    Query<SalesRep> query = ofy().load().type(SalesRep.class);
    if (count != null) query.limit(count);
    if (cursorString != null && cursorString != "") {
      query = query.startAt(Cursor.fromWebSafeString(cursorString));
    }
    List<SalesRep> records = new ArrayList<SalesRep>();
    QueryResultIterator<SalesRep> iterator = query.iterator();
    int num = 0;
    while (iterator.hasNext()) {
      records.add(iterator.next());
      if (count != null) {
        num++;
        if (num == count) break;
      }
    }
    //Find the next cursor
    if (cursorString != null && cursorString != "") {
      Cursor cursor = iterator.getCursor();
      if (cursor != null) {
        cursorString = cursor.toWebSafeString();
      }
    }
    return CollectionResponse.<SalesRep>builder().setItems(records).setNextPageToken(cursorString).build();
  }
  /**
   * This inserts a new <code>SalesRep</code> object.
   * @param SalesRep The object to be added.
   * @return The object to be added.
   */
  @ApiMethod(name = "insertSalesRep")
  public SalesRep insertSalesRep(SalesRep SalesRep) throws ConflictException {
    //If if is not null, then check if it exists. If yes, throw an Exception
    //that it is already present
    if (SalesRep.name != null) {
      if (findRecord(SalesRep.name) != null) {
        throw new ConflictException("Object already exists");
      }
    }
    //Since our @Id field is a Long, Objectify will generate a unique value for us
    //when we use put
    ofy().save().entity(SalesRep).now();
    return SalesRep;
  }
  /**
   * This updates an existing <code>SalesRep</code> object.
   * @param SalesRep The object to be added.
   * @return The object to be updated.
   */
  @ApiMethod(name = "updateSalesRep")
  public SalesRep updateSalesRep(SalesRep SalesRep)throws NotFoundException {
    if (findRecord(SalesRep.name) == null) {
      throw new NotFoundException("SalesRep Record does not exist");
    }
    ofy().save().entity(SalesRep).now();
    return SalesRep;
  }
  /**
   * This deletes an existing <code>SalesRep</code> object.
   * @param id The id of the object to be deleted.
   */
  @ApiMethod(name = "removeSalesRep")
  public void removeSalesRep(@Named("id") String id) throws NotFoundException {
    SalesRep record = findRecord(id);
    if(record == null) {
      throw new NotFoundException("SalesRep Record does not exist");
    }
    ofy().delete().entity(record).now();
  }

  private SalesRep findRecord(String name) {
    return ofy().load().type(SalesRep.class).id(name).now();
    //or return ofy().load().type(Quote.class).filter("id",id).first.now();
  }


  @ApiMethod(name = "listTasks")
  public CollectionResponse<TaskDetail> listTask(@Nullable @Named("cursor") String cursorString,
      @Nullable @Named("count") Integer count) {
    Query<TaskDetail> query = ofy().load().type(TaskDetail.class);
    if (count != null) query.limit(count);
    if (cursorString != null && cursorString != "") {
      query = query.startAt(Cursor.fromWebSafeString(cursorString));
    }
    List<TaskDetail> records = new ArrayList<TaskDetail>();
    QueryResultIterator<TaskDetail> iterator = query.iterator();
    int num = 0;
    while (iterator.hasNext()) {
      records.add(iterator.next());
      if (count != null) {
        num++;
        if (num == count) break;
      }
    }
    //Find the next cursor
    if (cursorString != null && cursorString != "") {
      Cursor cursor = iterator.getCursor();
      if (cursor != null) {
        cursorString = cursor.toWebSafeString();
      }
    }
    return CollectionResponse.<TaskDetail>builder().setItems(records).setNextPageToken(cursorString).build();
  }

  @ApiMethod(name = "insertTask")
  public TaskDetail insertTaskDetail(TaskDetail SalesRep) throws ConflictException {
    //If if is not null, then check if it exists. If yes, throw an Exception
    //that it is already present
    if (SalesRep.taskName != null) {
      if (findRecordTask(SalesRep.taskName) != null) {
        throw new ConflictException("Object already exists");
      }
    }
    //Since our @Id field is a Long, Objectify will generate a unique value for us
    //when we use put
    ofy().save().entity(SalesRep).now();
    return SalesRep;
  }

  private TaskDetail findRecordTask(String name) {
    return ofy().load().type(TaskDetail.class).id(name).now();
    //or return ofy().load().type(Quote.class).filter("id",id).first.now();
  }
}
